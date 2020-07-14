import {Component, OnInit} from '@angular/core';
import {Author} from '../author';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthorService} from '../author.service'
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-update-author',
  templateUrl: './update-author.component.html',
  styleUrls: ['./update-author.component.css']
})
export class UpdateAuthorComponent implements OnInit {

  id: number;
  author: Author;
  error: HttpErrorResponse;

  constructor(private route: ActivatedRoute, private router: Router,
              private authorService: AuthorService) {
  }

  ngOnInit() {
    this.author = new Author();
    this.id = this.route.snapshot.params['id'];

    this.authorService.getAuthor(this.id)
      .subscribe(data => {
        console.log(data);
        this.author = data;
      }, error => console.log(error));
  }

  updateAuthor() {
    this.authorService.updateAuthor(this.id, this.author)
      .subscribe(data => {
        console.log(data);
        this.author = new Author();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit() {
    this.updateAuthor();
  }

  gotoList() {
    this.router.navigate(['/authors']);
  }

}
