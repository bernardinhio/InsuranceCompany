In this project, I get from the backend a list of profiles with their profile IDs and their data, then when I clicking in each profile, I call the backend again to get either a list of TimelineEvents or a list of HealthPrompts.

In these 3 screens I implemented RecyclerViews that show data I get with Retrofit and Coroutines and also load image with Glide Library:


Some screenshots:

List of Profiles

![Screenshot_1593413674](https://user-images.githubusercontent.com/20923486/85982498-41749f00-b9e6-11ea-94c4-ce6b41fdbd71.png) 


List of Timeline Events for a specific profile

![Screenshot_1593413685](https://user-images.githubusercontent.com/20923486/85982548-53564200-b9e6-11ea-891b-66eeb8e7e027.png) 


List of Health Prompt for a specific Profile

![Screenshot_1593413695](https://user-images.githubusercontent.com/20923486/85982602-6ec14d00-b9e6-11ea-866e-d5f1b6d36a9d.png)


I created an architecture to organize the code especially for future real use. The packages I created are: 
datamodel, view, retrofit service, retrofil model, adapter (for RecyclerViews), model classes for the UI data of items on the RecyclerViews

I used the Library Glide to load the images from url because it is in my opinion the best &  easiest library that manages the “out of memory” problems commonly faced in  Apps that have a lot of images or big lists of items with images.

I used Coroutines Channel to send & receive data when the Retrofit backend response from Retrofit is ready. So it gives me the behavior of observable-observer pattern between the DataProvider class and the Views ( Activities). I could also have used RxJava ReplaySubject inside the DataProvider but this time I used Couroutines Channels.



My steps to use Retrofit in the project:

1. I created a Pojo classes for the data models of the responses of Retrofit. I copied-pasted the Json into some free online website http://pojo.sodhanalibrary.com/  then I converted the result of this website into Kotlin because it is more beautiful + powerful 😊

2. I created Singleton Retrofit Instance

3. I create the Interface for the different Retrofit calls that I  need for the project and I might need to add later in a real bigger project. Two of the calls are using a Dynamic End-Points, so I added a variable profile_id to the GET method.

4. I create a custom BackendStatus messages to handle the different failures cases of Retrofit. I specified these failures:

SocketTimeoutException
UnknownHostException
ConnectException
JSONException
JsonSyntaxException
IOException


5. Then I wrote the code of backend calls inside my DataProvider class where I used the created Retrofit instance and the signatures of the different retrofit Calls.

Thanks 😊 

