import 'dart:async';
import 'Note.dart';

import 'package:angular/core.dart';


@Injectable()
class NotesService {

  int idCount = 3;

  List<Note> mockNoteList = getNotes();

  List<Note> getNoteList() => mockNoteList;

  NoteService() {
    mockNoteList = getNotes();
  }

  void deleteNote(Note note) {
    mockNoteList.remove(note);
  }

  void addNote(Note note) {
    note.id = idCount +1;
    idCount++;
    mockNoteList.add(note);
  }

  void updateNote(Note updatedNote){

    for(int i = 0; i < mockNoteList.length; i++){
      if(mockNoteList[i].id == updatedNote.id){
        mockNoteList[i].relevance = updatedNote.relevance;
        mockNoteList[i].headline = updatedNote.headline;
        mockNoteList[i].due = updatedNote.due;
        mockNoteList[i].lastEdited = new DateTime.now().toUtc();
      }
    }


  }
}

  List<Note> getNotes() {
    List<Note> newList = [];

    Note note1 = new Note();
    note1.id =  1;
    note1.author = "User 1";
    note1.created = new DateTime.now();
    note1.due = new DateTime.now();
    note1.headline = "first note";
    note1.lastEdited = new DateTime.now();
    note1.relevance = Relevance.NORMAL;
    //idCount++;

    Note note2 = new Note();
    note2.id =  2;
    note2.author = "User 2";
    note2.created = new DateTime.now();
    note2.due = new DateTime.now();
    note2.headline = "second note";
    note2.lastEdited = new DateTime.now();
    note2.relevance = Relevance.IMPORTANT;

    Note note3 = new Note();
    note3.id =  3;
    note3.author = "User 3";
    note3.created = new DateTime.now();
    note3.due = new DateTime.now();
    note3.headline = "third note";
    note3.lastEdited = new DateTime.now();
    note3.relevance = Relevance.URGENT;

    newList.add(note1);
    newList.add(note2);
    newList.add(note3);

    return newList;
  }


