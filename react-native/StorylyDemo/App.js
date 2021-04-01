import React from 'react';
import {
  SafeAreaView,
  Animated,
  StatusBar,
  Button,
  Easing
} from 'react-native';

import FastImage from 'react-native-fast-image';
import { Storyly } from 'storyly-react-native';

const HEIGHT = 100;
const PADDING_TOP = 10;

const App = () => {
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
      <FastImage
        style={{ width: 200, height: 200 }}
            source={{
                uri: 'https://unsplash.it/400/400?image=1',  
            }}
        />
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
      </SafeAreaView>
    </>
  );
};

export default App;
