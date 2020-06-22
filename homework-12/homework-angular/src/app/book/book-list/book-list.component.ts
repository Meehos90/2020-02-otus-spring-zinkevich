import {Observable} from "rxjs";
import {BookService} from "../book.service";
import {Book} from "../book";
import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {TokenStorageService} from "../../auth/token-storage.service";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  books: Observable<Book[]>;
  private roles: string[];
  public authority: string;

  constructor(private bookService: BookService, private router: Router, private tokenStorage: TokenStorageService) {
  }

  ngOnInit() {
    this.reloadData();
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      if(this.roles.some(role => role === 'ROLE_ADMIN')) {
        this.authority = 'admin';
      } else {
        this.authority = 'user';
      }
    }
  }

  reloadData() {
    this.books = this.bookService.getBooksList();
  }

  deleteBook(id: number) {
    this.bookService.deleteBook(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  bookDetails(id: number) {
    this.router.navigate(['books/details', id]);
  }

  createBook() {
    this.router.navigate(['books/add']);
  }

  updateBook(id: number) {
    this.router.navigate(['books/update', id])
  }

}
