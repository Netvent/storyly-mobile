import 'package:flutter/material.dart';
import 'package:storyly_demo/update_state_example.dart';
import 'package:storyly_flutter/storyly_flutter.dart';

import 'scroll_example.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'StorylyDemo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage(title: 'Storyly Demo Page'),
    );
  }
}

class HomePage extends StatefulWidget {
  HomePage({
    Key? key,
    required this.title,
  }) : super(key: key);

  final String title;

  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const STORYLY_INSTANCE_TOKEN =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

  late StorylyViewController storylyViewController;

  void onStorylyViewCreated(StorylyViewController storylyViewController) {
    this.storylyViewController = storylyViewController;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Container(
              margin: const EdgeInsets.only(top: 10),
              height: 120,
              child: StorylyView(
                onStorylyViewCreated: onStorylyViewCreated,
                androidParam: StorylyParam()
                  ..storylyId = STORYLY_INSTANCE_TOKEN
                  ..storyGroupListEdgePadding = 20
                  ..storyGroupListPaddingBetweenItems = 20,
                iosParam: StorylyParam()
                  ..storylyId = STORYLY_INSTANCE_TOKEN
                  ..storyGroupListEdgePadding = 20
                  ..storyGroupListPaddingBetweenItems = 20,
                storylyLoaded: (storyGroupList) => print("storylyLoaded"),
                storylyLoadFailed: (errorMessage) => print("storylyLoadFailed"),
                storylyActionClicked: (story) => print("storylyActionClicked"),
                storylyEvent: (event, storyGroup, story, storyComponent) {
                  print("storylyEvent -> $event");
                },
                storylyStoryShown: () => print("storylyStoryShown"),
                storylyStoryDismissed: () => print("storylyStoryDismissed"),
                storylyUserInteracted: (storyGroup, story, storyComponent) {
                  print("storylyUserInteracted");
                },
              ),
            ),
            TextButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => ScrollExample()),
                );
              },
              child: Text("Scroll Example"),
            ),
            TextButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => UpdateStateExample()),
                );
              },
              child: Text("Update State Example"),
            ),
          ],
        ),
      ),
    );
  }
}
