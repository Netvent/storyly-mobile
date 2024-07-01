import 'dart:math';

import 'package:flutter/material.dart';
import 'package:storyly_demo/constants.dart';
import 'package:storyly_flutter/storyly_flutter.dart';
import 'package:storyly_monetization_flutter/storyly_monetization_flutter.dart';



class MultiScreenExample extends StatefulWidget {
  const MultiScreenExample({Key? key}) : super(key: key);

  @override
  _MultiScreenExampleState createState() => _MultiScreenExampleState();
}

class _MultiScreenExampleState extends State<MultiScreenExample> {
  
  final PageController _pageController = PageController();
  int selectedIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Multi-Screen Example"),
      ),
      body: PageView(
      controller: _pageController,
        children: <Widget>[
          FirstWidget(),
          SecondWidget(),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        selectedItemColor: Theme.of(context).textSelectionTheme.selectionColor,
        currentIndex: selectedIndex,
        onTap: ((value) {
          _pageController.jumpToPage(value);
          setState(() {
            selectedIndex = value;
          });
        }),
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            backgroundColor: Colors.grey,
            icon: Icon(Icons.search),
            label: 'Storyly',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.list),
            label: 'SubScreen',
          ),
        ],
      ),
    );
  }
}

class FirstWidget extends StatelessWidget {
  late StorylyViewController storylyViewController;

  void onStorylyViewCreated(StorylyViewController storylyViewController) {
    this.storylyViewController = storylyViewController;
    StorylyMonetization.setAdViewProvider(
    storylyViewController.getViewId(),
    AdViewProvider(
        adMobAdUnitId: "ca-app-pub-3940256099942544/2247696110",
        adMobAdExtras: {
          "npa": "1",
          "aa": {"test": "a"}
        }));
  }


  @override
  Widget build(BuildContext context) {
    return createFirstScreen();
  }
  

  Widget createFirstScreen() {
    return ListView(
        children: [
          const Padding(
            padding: EdgeInsets.all(8.0),
            child: Text(
              "Welcome to Storyly",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 20,
              ),
            ),
          ),
          storylyArea(),
          const Padding(
            padding: EdgeInsets.all(8.0),
            child: Text(
              "Latest from our blog",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 20,
              ),
            ),
          ),
          storylyArea(),
          ...latestPosts(50),
        ],
      );
  
  }

  Widget storylyArea() {
    return Container(
      height: 120,
      margin: const EdgeInsets.symmetric(vertical: 10.0),
      child: StorylyView(
        onStorylyViewCreated: onStorylyViewCreated,
        androidParam: StorylyParam()
          ..storylyId = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjU1NiwiYXBwX2lkIjoxMzg5LCJpbnNfaWQiOjE5MDE0fQ.xFHBIGX6vaP0QGuW88W3y5T0aYKl86iCHvZ2iiF1k44",
        iosParam: StorylyParam()
          ..storylyId = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjU1NiwiYXBwX2lkIjoxMzg5LCJpbnNfaWQiOjE5MDE0fQ.xFHBIGX6vaP0QGuW88W3y5T0aYKl86iCHvZ2iiF1k44",
      ),
    );
  }

  List<Widget> latestPosts(int postCount) {
    return List<Widget>.generate(
      postCount,
      (_) {
        final randomColor = (Random().nextDouble() * 0xFFFFFF).toInt();
        return Container(
          color: Color(randomColor).withOpacity(1.0),
          height: 150,
        );
      },
    );
  }
}

class SecondWidget extends StatelessWidget {
   @override
  Widget build(BuildContext context) {
    return createSecondScreen(context);
  }
  

  Widget createSecondScreen(BuildContext context) {
    return Row(
        children: [
          SizedBox(
            width: 120,
            height: 120,
            child: ElevatedButton(
              onPressed: () {
              ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                  content: Text("Button Clicked"),
              ));
            }, child: const Text("Click Test"))
          )  
        ],
      );
  }
}