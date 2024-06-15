# CSE364-unist group 7 - User documentation

This is guideline for user who want to enjoy our UniMovie service.

-**The commit name 'root' is 'JungwooMoon-20191352' because he commit by ubuntu system** 


## What is UniMovie App about

The app provides...
* The seasonal movie recommendation system to solve users' concerns about choosing a movie due to the overflowing ott system

* The face-swap system to satisfy their fun and desire to appear in a movie

* The movie prediction system to inform users of information by predicting the success of a movie before releasing it or watching it in a movie theater

## How it works and How to use by Each page and what each page does

### Main page

![Main page](images/Homepage.PNG)

This is main page of our UniMovie App. And we can interact with our feature using bars abvoe of the page. 
This page is just Homepage. Therefore, the aim is to give users a choice of three features.

Here is our menu bar. And all page have menu bar.

![Bar](images/Menubar.PNG)

* If user click Home button user will come back to the main page, wnhereever user locate other feature.
* If user click Movie Recommendations button, user will go to our movie recommendation feature page.
* If user click Face Swap with Movie Poster, user will go to our Face Swap feature page.
* If user click Movie Success Predict, user will go to our Movie Prediction feature page.

### Movie Recommendation Page

![Movie Recommendation Page](images/Rec_0.PNG)

This is the page of our Movie Recommendation feature. user can interact with bars above the page to interact with other feautre.
And also user can use our UI to get the movie recommendation by season and genre.

User can choose 4 season which are 'summer', 'winter', 'spring', 'fall'. And user can optionally choose genre which have 18 category.

User have to click 'Get Top 10 Recommendation' Button first, because we want to show users to the recommended movies that we are most confident in. So, when user click the 'Get Top 10 Recommendation' Button, user can press 'Get Random Recommendation' Button.
So, every time user change the genre and season, user must click the 'Get Top 10 Recommendation' Button first.

![Output Result](images/Rec_2.PNG)

This is the output that we provide for users. Output contains the data which are 'movie_name', 'season', 'genre', 'rating'. 
If user don't like the result user can change the input of season and genre or user can press 'Get Random Recommendation' Button.

![Error Situation](images/Rec_3.PNG)

If user want to recommend movie, user have to choose season. 
If user didn't choose season and press the 'Recommendaiton Button'. User can see the 'Error message'. 
But genre is optional, so user can recommend movie only provide season without genre.

