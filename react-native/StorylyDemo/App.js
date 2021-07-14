import * as React from 'react';
import {
  SafeAreaView,
  Animated,
  StatusBar,
  Button,
  Easing,
  View,
  Text,
} from 'react-native';

import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import { Storyly } from 'storyly-react-native';

const HEIGHT = 100;
const PADDING_TOP = 10;

function HomeScreen({navigation}) {
  const height = new Animated.Value(HEIGHT);
  const paddingTop = new Animated.Value(PADDING_TOP);

  const openStoryly = () => {
    Animated.timing(height, {
      toValue: HEIGHT,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();

    Animated.timing(paddingTop, {
      toValue: PADDING_TOP,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();
  }

  const closeStoryly = () => {
    Animated.timing(height, {
      toValue: 0,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();

    Animated.timing(paddingTop, {
      toValue: 0,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();
  }

  return (
    <>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <Button title={'Open'} onPress={openStoryly}/>
        <Button title={'Close'} onPress={closeStoryly}/>
        <Animated.View
          style={{
            paddingTop: paddingTop,
            height: height,
            backgroundColor: "#fff",
          }}
        >
          <Storyly
            style={{
              height: "100%",
            }}
            storyGroupSize="small"
            storylyId="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40"
          />
        </Animated.View>
        <Button
          onPress={() => navigation.navigate('Modal1')}
          title="Open Modal 1"
        />
      </SafeAreaView>
    </>
  );
}

function ModalScreen1({ navigation }) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text style={{ fontSize: 30, padding:15 }}>This is a modal 1!</Text>
      <Button 
        onPress={() => navigation.goBack()} 
        title="Dismiss"
      />
      <Button
        onPress={() => navigation.navigate('Modal2')}
        title="Open Modal 2"
      />
    </View>
  );
}

function ModalScreen2({ navigation }) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text style={{ fontSize: 30, padding: 15 }}>This is a modal 2!</Text>
      <Button
        onPress={() => navigation.goBack()}
        title="Dismiss"
      />
    </View>
  );
}

const Stack = createStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Modal1" component={ModalScreen1} />
        <Stack.Screen name="Modal2" component={ModalScreen2} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;
