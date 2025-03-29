import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:convert';
import 'dart:math';
import 'package:http/http.dart';
import 'contact.dart';
class CapitalExplorerPage extends StatefulWidget {
  @override
  _CapitalExplorerPageState createState() => _CapitalExplorerPageState();
}

class _CapitalExplorerPageState extends State<CapitalExplorerPage> {
  final List<Map<String, dynamic>> predefinedCountries = [
    {'name': 'France', 'asset': 'assets/lang/fr.json'},
    {'name': 'Japan', 'asset': 'assets/lang/jp.json'},
    {'name': 'Germany', 'asset': 'assets/lang/de.json'},
    {'name': 'Brazil', 'asset': 'assets/lang/br.json'},
    {'name': 'United States', 'asset': 'assets/lang/us.json'},
    {'name': 'United Kingdom', 'asset': 'assets/lang/uk.json'},
    {'name': 'Australia', 'asset': 'assets/lang/au.json'},
    {'name': 'Canada', 'asset': 'assets/lang/ca.json'},
    {'name': 'Russia', 'asset': 'assets/lang/ru.json'},
    {'name': 'China', 'asset': 'assets/lang/cn.json'},
    {'name': 'Italy', 'asset': 'assets/lang/it.json'},
    {'name': 'South Korea', 'asset': 'assets/lang/kr.json'},
    {'name': 'Spain', 'asset': 'assets/lang/es.json'},
    {'name': 'Mexico', 'asset': 'assets/lang/mx.json'},
    {'name': 'Argentina', 'asset': 'assets/lang/ar.json'},
    {'name': 'Turkey', 'asset': 'assets/lang/tr.json'},
    {'name': 'South Africa', 'asset': 'assets/lang/za.json'},
    {'name': 'Indonesia', 'asset': 'assets/lang/id.json'}
  ];

  List<Map<String, dynamic>> _displayedCountries = [];
  final _random = Random();
  final Color primaryBlue = Color(0xFF1C6DD0); // Front page blue color

  @override
  void initState() {
    super.initState();
    _fetchCountriesFromAPI();
  }

  Future<void> _fetchCountriesFromAPI() async {
    try {
      final response = await get(Uri.parse('https://restcountries.com/v3.1/all'));
      if (response.statusCode == 200) {
        final List data = jsonDecode(response.body);

        final filteredCountries = data.where((country) {
          return predefinedCountries.any((predefined) =>
              predefined['name'] == country['name']['common']);
        }).map((country) {
          final predefined = predefinedCountries.firstWhere(
              (pre) => pre['name'] == country['name']['common']);
          return {
            'name': country['name']['common'],
            'capital': country['capital'] != null && country['capital'].isNotEmpty
                ? country['capital'][0]
                : 'N/A',
            'region': country['region'] ?? 'Unknown',
            'population': country['population'] ?? 'Unknown',
            'area': country['area'] ?? 'Unknown',
            'asset': predefined['asset'],
          };
        }).toList();

        filteredCountries.shuffle(_random);
        setState(() {
          _displayedCountries = filteredCountries.take(3).toList(); // Show 3 cards
        });
      } else {
        throw Exception('Failed to fetch countries. Status: ${response.statusCode}');
      }
    } catch (error) {
      print('Error fetching countries: $error');
    }
  }

  Future<Map<String, dynamic>> loadLocalizedContent(String assetPath) async {
    final String response = await rootBundle.loadString(assetPath);
    return jsonDecode(response);
  }

  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Explore Capitals',
          style: TextStyle(
            fontSize: 22,
            fontWeight: FontWeight.bold,
            color: Colors.white,
          ),
        ),
        centerTitle: true,
        backgroundColor: primaryBlue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Expanded(
              child: _displayedCountries.isEmpty
                  ? Center(child: CircularProgressIndicator())
                  : ListView.builder(
                      itemCount: _displayedCountries.length,
                      itemBuilder: (context, index) {
                        final country = _displayedCountries[index];
                        return Card(
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15.0),
                          ),
                          elevation: 5,
                          margin: EdgeInsets.symmetric(vertical: 10),
                          color: primaryBlue, // Card background in blue
                          child: Padding(
                            padding: EdgeInsets.all(16),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  country['name'],
                                  style: TextStyle(
                                    fontSize: 20,
                                    fontWeight: FontWeight.bold,
                                    color: Colors.white, // White text
                                  ),
                                ),
                                SizedBox(height: 5),
                                Text(
                                  'Capital: ${country['capital']}',
                                  style: TextStyle(fontSize: 16, color: Colors.white),
                                ),
                                Text(
                                  'Region: ${country['region']}',
                                  style: TextStyle(fontSize: 16, color: Colors.white),
                                ),
                                Text(
                                  'Population: ${country['population']}',
                                  style: TextStyle(fontSize: 16, color: Colors.white),
                                ),
                                Text(
                                  'Area: ${country['area']} sq.km',
                                  style: TextStyle(fontSize: 16, color: Colors.white),
                                ),
                                SizedBox(height: 10),
                                Align(
                                  alignment: Alignment.centerRight,
                                  child: ElevatedButton(
                                    style: ElevatedButton.styleFrom(
                                      backgroundColor: Colors.white, // White button
                                      shape: RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(10),
                                      ),
                                    ),
                                    onPressed: () async {
                                      final localizedContent =
                                          await loadLocalizedContent(country['asset']);
                                      showDialog(
                                        context: context,
                                        builder: (context) => AlertDialog(
                                          title: Text(
                                            localizedContent['title'],
                                            style: TextStyle(fontWeight: FontWeight.bold),
                                          ),
                                          content: Text(localizedContent['content']),
                                          actions: [
                                            TextButton(
                                              onPressed: () {
                                                Navigator.pop(context);
                                              },
                                              child: Text(
                                                'Close',
                                                style: TextStyle(color: primaryBlue),
                                              ),
                                            ),
                                          ],
                                        ),
                                      );
                                    },
                                    child: Text(
                                      'Learn More',
                                      style: TextStyle(fontSize: 16, color: primaryBlue),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),
                        );
                      },
                    ),
            ),
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Expanded(
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: primaryBlue,
                      padding: EdgeInsets.symmetric(vertical: 14),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                    onPressed: _fetchCountriesFromAPI,
                    child: Text(
                      'Refresh',
                      style: TextStyle(fontSize: 18, color: Colors.white),
                    ),
                  ),
                ),
                SizedBox(width: 10),
                Expanded(
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      backgroundColor: primaryBlue,
                      padding: EdgeInsets.symmetric(vertical: 14),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                    ),
                    onPressed: () {
                      Navigator.push(
                        context,
                      MaterialPageRoute(builder: (context) => Contact()), // Navigate to ContactPage
                    );
                     },
                    child: Text(
                      'Next',
                      style: TextStyle(fontSize: 18, color: Colors.white),
                    ),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
