import 'package:fassign/pages/capital.dart';
import 'package:flutter/material.dart';

class Front extends StatefulWidget {
  const Front({super.key});

  @override
  State<Front> createState() => _FrontState();
}

class _FrontState extends State<Front> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Container(
        
        padding: EdgeInsets.only(top: 100.0,left: 20.0),
        child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
        Image.asset("assets/images/img2.jpg"),
        SizedBox(height:90.0,),
        
        Text(
          "Explore the Beating Hearts of Nations: Start Your Capital Quest",
          style: 
          TextStyle(
          color: Color(0xFF1C6DD0),
          fontSize: 30.0,
          fontWeight:FontWeight.bold,
          fontFamily: 'Palatino',
          
          ),
          
        ),
        Expanded(
            child: Align(
              alignment: Alignment.bottomRight,
              child: Padding(
                padding: const EdgeInsets.only(bottom: 30.0, right: 20.0), // Padding for spacing
                child: GestureDetector(
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => CapitalExplorerPage()), // Navigate to NextPage
                    );
                  },
                  child: Container(
                    padding: EdgeInsets.symmetric(vertical: 15.0, horizontal: 30.0), // Padding for better tap area
                    decoration: BoxDecoration(
                      color: Color(0xFF1C6DD0), // Blue Background
                      borderRadius: BorderRadius.circular(10.0), // Rounded Corners
                    ),
                    child: Text(
                      "Dive in",
                      style: TextStyle(
                        color: Colors.white, // White Text for contrast
                        fontSize: 25.0,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Palatino',
                      ),
                    ),
                  ),
                ),
              ),
            ),
          ),       
      ],),),
    );
  }
}


