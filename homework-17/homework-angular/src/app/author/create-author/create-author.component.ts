import {AuthorService} from '../author.service';
import {Author} from '../author';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-create-author',
  templateUrl: './create-author.component.html',
  styleUrls: ['./create-author.component.css']
})
export class CreateAuthorComponent implements OnInit {

  author: Author = new Author();
  submitted = false;
  error: HttpErrorResponse;

  constructor(private authorService: AuthorService, private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.authorService.createAuthor(this.author)
      .subscribe(data => {
        console.log(data);
        this.author = new Author();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/authors']);
  }

}
