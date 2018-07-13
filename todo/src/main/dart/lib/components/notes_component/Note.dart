import 'package:intl/intl.dart';

//Note Klasse als Datenstruktur für die Note Entität und Helferfunktinen für JSON
class Note {

  int id;
  String author;
  String headline;
  DateTime created;
  DateTime lastEdited;
  DateTime due;
  Relevance relevance;
  String content;

  Note(){}

  //Noteobjekt wird zu einer JSON kompatiblen Map umgeandelt
  Map toJson() => {
    'id' : id,
    'author' : author,
    'headline' : headline,
    'created' : DateTimeToJson(created),
    'lastEdited' : DateTimeToJson(lastEdited),
    'due' : DateTimeToJson(due),
    'relevance' : relevance.toString(),
    'content' : content
  };

  //Noteobjekt wird aus einer JSON Map erstellt
    factory Note.fromJson(Map<String, dynamic> note){
    Note newNote = new Note();
    newNote.id = _toInt(note['id']);
    newNote.headline = note['headline'];
    newNote.author = note['author'];
    //newNote.created = DateTime.parse(note['createdString']);
    //print(note['created']);
    //print(parseDateTimetoDate(note['created']));
    newNote.created = parseDateTimetoDate(note['created']);
    //newNote.lastEdited = DateTime.parse(note['lastEditedString']);
    newNote.lastEdited = parseDateTimetoDate(note['lastEdited']);
    //newNote.due = DateTime.parse(note['dueString']);
    newNote.due = parseDateTimetoDate(note['due']);
    newNote.relevance = enumFromString(note['relevance']);
    newNote.content = note['content'];

    return newNote;
  }

  String DateTimeToJson(DateTime dt){
      String dateString = dt.toString();
    return dateString;
  }

  //Hilfsfunkton um Enum in einen String umzuwandeln
  String RelevanceToJson(Relevance rel){
      print(rel.toString());
      String relString;
      print(rel == Relevance.NORMAL);
    if(rel == Relevance.NORMAL){
      relString = "Relevance.NORMAL";
    }
    else if(rel == Relevance.IMPORTANT){
      relString = "Relevance.IMPORTANT";
    }
    else if(rel == Relevance.URGENT){
      relString = "Relevance.URGENT";
    }
    print(relString);
    return relString;
  }

}

  int _toInt(id) => id is int ? id : int.parse(id);

//Hilfsfunkton um String in einem Enum umzuwandeln
  Relevance enumFromString(String enumString){
    print(enumString);
    Relevance rEnum;
    switch(enumString){
      case 'NORMAL' :
        rEnum = Relevance.NORMAL;
        break;
      case 'IMPORTANT' :
        rEnum = Relevance.IMPORTANT;
        break;
      case 'URGENT' :
        rEnum = Relevance.URGENT;
        break;
      default:
        rEnum = null;
        break;
    }
    rEnum.toString();
    return rEnum;
  }

  enum Relevance {

    NORMAL, IMPORTANT, URGENT

  }

DateTime parseDateTimetoDate(List<String> dtS){
  //dtS = dtS.toString();
  print(dtS);
  //String dateString = dtS[0].toString() + "-" + dtS[1].toString() + "-" + dtS[2].toString();
  if(_toInt(dtS[1]) < 10){
    dtS[1] = "0"+dtS[1].toString();
  }
  String dateString = dtS[0].toString() + dtS[1].toString() + dtS[2].toString();
  print(dateString);

  return DateTime.parse(dateString);
}