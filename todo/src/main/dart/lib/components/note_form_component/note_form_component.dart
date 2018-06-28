import 'package:angular/angular.dart';
import 'package:angular_components/angular_components.dart';

import 'package:todo/components/test_component/notes_service.dart';
import 'package:todo/components/test_component/Note.dart';
import 'package:todo/components/test_component/test_component.dart';

@Component(
  selector: 'note-form-component',
  templateUrl: 'note_form_component.html',
  directives: const[CORE_DIRECTIVES, TestComponent],
  providers: const [NotesService]
)
class NotesFormComponent{
  final NotesService notesService;

  NotesFormComponent(this.notesService);

void submitAddForm(String author, String headline, Relevance relevance){

  Note newNote = new Note();
  newNote.author = author;
  newNote.created = new DateTime.now();
  newNote.due = new DateTime.now();
  newNote.headline = headline;
  newNote.lastEdited = new DateTime.now();
  newNote.relevance = relevance;



}

}