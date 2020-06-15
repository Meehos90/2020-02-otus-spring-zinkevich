import {Author} from '../author';
import {Component, OnInit} from '@angular/core';
import {AuthorService} from '../author.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css']
})
export class AuthorDetailsComponent implements OnInit {

  id: number;
  author: Author;

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

}
