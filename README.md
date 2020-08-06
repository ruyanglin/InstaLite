# InstaLite

A mobile photo rating app that allows users to upload images, filter and rate images.

## Use
* The leftmost button loads the image set from https://www.student.cs.uwaterloo.ca/~cs349/w20/assignments/images
    * Pressing the button again will load the image set again

* Adding a single image will append it to the end of the view with rating of 0

* To clear the rating filter set the number of stars to 0
    * Any changes to the rating of a photo is updated right away
        * For example, if the filter is set to 3 stars and an image originally 3 stars is changed to 2 stars, 
        it will not appear in the main view

* To return to the main activity from the zoomed in activity, press the back button on the device or the back button

* Use the floating button to add a new image via URL
    * Toast widget is used to display errors
    
* APK is under the a4/apk directory (app-debug.apk)

>Stable running on:
>
>openjdk version "11.0.5" 2019-10-15
>
>Ubuntu 18.04.3 LTS
>
>Android SDK version 29


## Resources
<a target="_blank" href="https://icons8.com/icons/set/remove-image">Dotted icon</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>

<a target="_blank" href="https://icons8.com/icons/set/plus">Plus icon</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>

<div>Mulitple Images icon made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>