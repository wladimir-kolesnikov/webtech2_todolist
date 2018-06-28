import 'dart:async';
import 'Note.dart';
import 'dart:convert';
import 'package:http/http.dart';

import 'package:angular/core.dart';
import 'package:cleanTest2/src/in_memory_db_compnent/in_memory_db_component.dart';


//Notesservice der Interaktionsfunktionen von Note bezogenen Aufgaben mit dem Server übernimmt
@Injectable(
)
class NotesService {

  final InMemoryDatabaseService inMemoryDatabaseService;

  NotesService(this.inMemoryDatabaseService);

  List<Note> mockNoteList = [];

  //GET
  //Funktion holt alle Notes eines Users mit bestimmter id
  Future<List<Note>> getNoteList(int id) async {
    try{
      final response = await inMemoryDatabaseService.get('api/users/$id/notes');
      final notes = (_extractData(response) as List).map((json) => new Note.fromJson(json)).toList();
      print(_extractData(response));

      return notes;
    }
    catch(e){
      _handleError(e);
    }
  }

  //DELETE
 //Löscht eine Note mit bestimmter Note id eines Users
  Future deleteNote(Note note, int id) async {
    int NoteID = note.id;
    try{
      final response = await inMemoryDatabaseService.delete('api/users/$id/notes/$NoteID');
    }
    catch(e){
      _handleError(e);
    }
  }

  //POST
  //Fügt eine neue Note einem bestimmt User hinzu
 Future<Note> addNote(Note note, int id) async {
   try{
     final response = await inMemoryDatabaseService.post('api/users/$id/notes', headers: {'Conent-Type':'applicaton/json'},
         body: JSON.encode(note.toJson()));
     final returnNote = new Note.fromJson(_extractData(response));
     return returnNote;

   }
   catch(e){
     _handleError(e);
   }
 }

 //PUT
  //Updated die Daten einer Note
 Future updateNote(Note note, int id) async {
   int NoteID = note.id;
  try{
    final response = await inMemoryDatabaseService.put('api/users/$id/notes/$NoteID', headers: {'Conent-Type':'applicaton/json'},
        body: JSON.encode(note.toJson()));
    final returnNote = new Note.fromJson(_extractData(response));
    return returnNote;

  }
  catch(e){
    _handleError(e);
  }
 }

 //Dekodierung der JSON response
  dynamic _extractData(Response resp) => JSON.decode(resp.body)['data'];

  //Error handling
  Exception _handleError(dynamic e){
    print(e);
    return new Exception('Server error; cause $e');
  }
}