Given a spawarka
When set arguments like name: audi, model: a4, code: 4466
When add it to arraylist
Then adding should return audi for Spawarka object with code 4466

Given a spawarka
When set arguments like name: bmw, model: 7, code: 3
When add it to arraylist
Then adding should return bmw for Spawarka object with code 3
When set new name Volvo for spawarka with code 3
Then check if data has been updated for spawarka with code 3 and new name Volvo