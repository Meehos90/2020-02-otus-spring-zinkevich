import {Observable} from "rxjs";
import {AuthorService} from "../author.service";
import {Author} from "../author";
import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {TokenStorageService} from "../../auth/token-storage.service";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {
  authors: Observable<Author[]>;
  private roles: string[];
  public authority: string;

  constructor(private authorService: AuthorService, private router: Router, private tokenStorage: TokenStorageService) {
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
    this.authors = this.authorService.getAuthorsList();
  }

  deleteAuthor(id: number) {
    this.authorService.deleteAuthor(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  authorDetails(id: number){
    this.router.navigate(['authors/details', id]);
  }

  createAuthor() {
    this.router.navigate(['authors/add']);
  }

  updateAuthor(id: number) {
    this.router.navigate(['authors/update', id])
  }

}
