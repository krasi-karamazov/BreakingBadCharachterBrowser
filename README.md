# Breaking Bad Character browser
A simple client for the Breaking Bad API. The purpose of this application is to display data about all characters in the Breaking Bad series

The app is modularized and is using the Clean architecture for code structuring. Communication between components is implemented using the MVVM architecture.

The implemented requirements are marked as Done.
Below the Requirements are listed some TODOs that given time I would like to work on

### Requirements
∙ Create an app that consumes the Breaking Bad API
##### Done
∙ Screen 1 - display a list of characters with images and names
##### Done
∙ Screen 1 - filter characters by season appearance
##### Done
∙ Screen 1 - search characters by name/portion of a name
##### Done
∙ Screen 2 - display character details (image, name, occupation, etc)
##### Done
∙ Screen 2 - go back to Screen 1
##### Done
 

### TODO

∙ Work a bit on readability on some portions of the code

∙ Implement moreextensive unit testing. Right now, because of time constraints, the unit tests are concentrated in the model and domain portions, which makes sense, but it would be nessessary to have unit tests for the view models when they become a bit more logic/feature rich

∙ Spend more time manually testing

∙ Spend more time on saving state between configuration changes and screen transitions

∙ Work a bit on UI and UX as currently it's not optimal (maybe some animations)

∙ Work on optimizations where nessessary
