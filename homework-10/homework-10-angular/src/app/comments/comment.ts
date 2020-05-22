import {Book} from "../book/book";

export class Comment {
  id: number;
  content: string;
  book: Book = new Book();
}
