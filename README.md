# UGAChain Desktop Application

This is a little documentation about our desktop application for the UGAChain which will help you to push data in our blockChain.
In this documentation we will description the behavior of our application and all the questions we are discussing about.

## Compile and launch

```
mvn package
```

Launch :

```
./launch.sh
```

# Usage

Before using our application please, take a look to the configuration part of this file.

Our Desktop Application have a single objective : Send data to the blockChain.
You should have a .csv with the student name, his student ID, the year of the graduation and the diploma name on each line that describe a student. Of course the .csv file can have multiples lines inside.

UGAChain Desktop Application will read a csv file, that you need to drag and drop in the window OR that you'll browse to him with our button "browse" (Multiple drag and drop authorized)

When you'v put all the csv files you want to export in the program, just validate. A new Window will appears where you should choose the students that are actually graduated. When you'v selected all the students you want to just validate.

A new Window appears and ask you to validate then to insert you Security USB Key that you'r program manager have given to you. you MUST validate before inserting the USB on the computer.

If all the data on the USB are correct, a new Window appears and again you need to validate BEFORE removing the USB key. If everything is done correctly a message is written on the screen and the program will close itself.

## Configuration
In order to configurate UGA Desktop to your university, you need to modify "config.conf" located in "BlockChain-desktop/config"

this file is used to generated diploma, and is composed by :

 - University / School name
 - Language (Actually only "FR" / "EN")
 - University / School Logo path
 - Signature of the responsible path
 - Name of the responsible

exemple of a correct config.conf :
"*Polytech Grenoble,FR,logo.png,signature.png,Benjamin Franklin*"

## Exemple of correct csv file

exemple.csv :

*nom,prenom,annee,numero de diplome,diplome*

*first_name,last_name,year,diploma_number,diploma_name*


## Security questions

In order to prevent a non authorized usage of our application we are using a USB security system, that force the user to insert and remove a USB that are encrypted with security mesures. 

We thought that removing the USB key, is a way to protect our program from "computer leaving" : if someone let the computer openned and leave this room, nobody can use the program because the key will be ejected from the computer before the user's leave.


## Current test API

Actually we are using a test API for our tests, this API is composed by :

 - UGA/get?transaction=transactionID
					 - which returns a digest based on the transactionID
 
 
 - UGA/post
	 - which take a JSON file composed by { "digest":"value" }
	 - Will store the digest on the chain
	 - returns the transactionID of this transaction

## Languages used

 - All the application is written in Java + Swing
 - The test API is written in node-JS
