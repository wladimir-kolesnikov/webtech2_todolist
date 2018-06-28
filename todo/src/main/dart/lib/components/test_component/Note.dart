class Note {

  int id;
  String author;
  String headline;
  DateTime created;
  DateTime lastEdited;
  DateTime due;
  Relevance relevance;

}

  enum Relevance {

    NORMAL, IMPORTANT, URGENT

  }