drop database if exists game;
create database Game;
use Game;
create table GuessGame(
	gameID INT PRIMARY KEY AUTO_INCREMENT,
    gameStatus varchar(15),
    answer varchar(4) ,
    number int not null
);

create table round(
	roundID int primary key auto_increment,
    timePlayed varchar(30),
    gameID int not null,
    guessAnswer varchar(6), 
	foreign key (gameID) references GuessGame(gameID)
    );
  select * from guessgame ;
  select * from round;