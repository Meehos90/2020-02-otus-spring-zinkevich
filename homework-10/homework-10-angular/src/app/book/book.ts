import {Author} from "../author/author";
import {Genre} from "../genre/genre";

export class Book {
  id: number;
  title: string;
  author: Author = new Author();
  genre: Genre = new Genre();
}
