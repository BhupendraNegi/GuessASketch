# GuessASketch
Guess a Sketch - An Android Application build to help the physcially challenged people who can't speak. This will help themto translate there sketches into meaning full words.

![Image of GuessASketch](https://github.com/BhupendraNegi/GuessASketch/blob/master/pictures/Screenshot_2018-12-05-00-53-36-655_edu.mum.ml.group7.guessasketch.android.png)


![Image of GuessASketch](https://github.com/BhupendraNegi/GuessASketch/blob/master/pictures/Screenshot_2018-12-05-00-53-41-347_edu.mum.ml.group7.guessasketch.android.png)
## It has 3 layers:-
- [ ] Guess a Sketch Android App, which will help to draw the sketches. The Android app was build such that it doesn’t save sketches locally, but instead, send them over the internet in a POST HTTP request to the middle layer. Which in turn would return a JSON format response with labels it predicts would match this sketch. 

Guess a Sketch Android app would then display those predictions to the user in a list of radio buttons, There also would be an option to suggest a different label.  If the predictions provided by the Middle layer were correct and the user selects one of them, then that would be a positive feedback for the system, we simply guessed it right, this positive feedback would be used later on to retrain the engine and increase the confidence of this prediction. 

If the user didn’t find the label they were expecting, then they can choose the “Other (Please specify)” button, which would by then asks them to enter a label for the sketch the drew. This is called “Negative feedback”, this Label along with the sketch would be sent to the middle layer and would be used later on to retrain the system and introduce a new class (label) if number of images submitted by users matches a certain threshold (30 images, for now, it’s a requirement by tensorflow), In order to help faster builds for the Android app as well as better dependency handling we used a software project management tool and build management called Gradle (https://gradle.org/) 


- [ ] The middle layer is firebase.
#### Functionalities of Middle layers:
Guess a sketch, in this API call, the android app would send the image binary data, middle layer would forward this binary data along with a specially crafted HTTP POST request to the Guess-Sketch Engine, This engine would reply with a JSON string containing a list of predictions matching that sketch. Middle layer would then pass this JSON string back to the Android app.

* Positive Feedback: Android app sends the binary data of the image along with the label predicted by the Guess a sketch engine earlier, this image data along with that label would be kept inside a positive feedback folder and would be used later to retrain the system. Retraining would happen in this case by copying over all new images from the positive feedback folder to their appropriate locations inside the dataset folder and run the retraining script. 

* Negative Feedback: Android app sends the binary data of the image along with the label entered by the user, this image data along with that label would be kept inside a negative feedback folder and would be used later to retrain the system once the number of images reaches a threshold (30 images in this case). Retraining would happen copying over this label-named new folder along with images inside it to the dataset folder and run the retraining script. 

- [ ] Guess A Sketch Engine is python based FastCGI script that uses TensorFlow (https://www.tensorflow.org/) inception engine to predict images sent by the middle layer and responds back in a JSON format string. 

Tensorflow initially comes untrained, we trained that system by drawing sketches for 12 different classes (refer to Dataset section above), once trained the system generates a bottleneck files, labels file and a .pb file which acts like a knowledge database that tensorflow engine can use later on to make predictions.

Guess a Sketch Engine was deployed as a FastCGI script, using Apache FastCGI Module (https://httpd.apache.org/mod_fcgid/) the benefit of this approach is that we will always have parameterized number of script instances loaded in memory and waiting to serve requests.

### It also used several python libraries in order to achieve the functionality needed: 

* Flipflop (https://pypi.python.org/pypi/flipflop) : FastCGI wrapper for WSGI applications
* Werkzeug (http://werkzeug.pocoo.org/) : WSGI utility library for Python that simplifies handling of HTTP Requests/Response and supports multipart/form-data uploads. 
* Tensorflow : The tensorflow inception engine python interface  
* JSON : to produce output in JSON format
