import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class Contact extends StatefulWidget {
  const Contact({super.key});

  @override
  State<Contact> createState() => _ContactState();
}

class _ContactState extends State<Contact> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _ageController = TextEditingController();
  final TextEditingController _mailController = TextEditingController();
  final TextEditingController _messageController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Text(
        "Contact",
        style:TextStyle(color: Colors.white),
        ),
        centerTitle: true,
        iconTheme: IconThemeData(color: Colors.black),
        elevation: 0,
        backgroundColor: Color(0xFF1C6DD0),
      ),
      body: Column(
        
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Image.asset("assets/images/img1.webp"),
          Expanded(
            child:Container(
              padding: const EdgeInsets.all(20.0),
              decoration: BoxDecoration(
                color: Color(0xFF1C6DD0),
                borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                TextField(
                controller: _nameController,
               decoration: InputDecoration(labelText: 'Name',
               labelStyle: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
               ),
                style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),

              ),
              TextField(
               controller: _ageController,
               decoration: InputDecoration(labelText: 'Age',
                labelStyle: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),

               ),
               keyboardType: TextInputType.number,
               style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),

             ),
             TextField(
              controller: _mailController,
               decoration: InputDecoration(labelText: 'Mail',
               labelStyle: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
               ),
                style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),

              ),
             TextField(
                controller: _messageController,
               decoration: InputDecoration(labelText: 'Message Ratings',
               labelStyle: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
               ),
                style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),

              ),
              SizedBox(height:30.0,),
              ElevatedButton(
            onPressed: () {
              final String name = _nameController.text;
              final int age = int.parse(_ageController.text);
              final String mail=_mailController.text;
              final String message=_messageController.text;
              FirebaseFirestore.instance.collection('users').add({
                'name': name,
                'age': age,
                'message':message,
                'mail':mail,
              });

              _nameController.clear();
              _ageController.clear();
              _messageController.clear();
              _mailController.clear();
            },
            child: Text('Add'),
          ),
         

                ],
              ),
      



            )
            
            ),
           
        ],
      ),
    );
  }
}