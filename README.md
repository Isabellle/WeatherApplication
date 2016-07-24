# WeatherApplication
Weather Android Application that allows a user to retrieve the weather of a city. City are stored and displayed in a viewpager.

Library used:
- Realm to store cities
- http://Forecast.io api to retrieve weather forecast
- Flick api to show a background that corresponds to the selected city
- Glide to display background images
- Custom font to display weather icon [https://github.com/erikflowers/weather-icons]

![alt tag](http://i.imgur.com/cDtsqIr.png)

Note: For the moment, cities are hard-coded.

-- TODO --
- ~~Let the user enter a city~~ **Done**
- ~~Keep cities in SharePreferences~~ **Done, but using Realm**
- Better handle the background image in Landscape mode
- Add more information regarding weather forecast
