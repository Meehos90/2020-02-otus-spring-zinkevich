import {Component, OnInit} from '@angular/core';
import {Genre} from '../genre';
import {ActivatedRoute, Router} from '@angular/router';
import {GenreService} from '../genre.service'
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-update-genre',
  templateUrl: './update-genre.component.html',
  styleUrls: ['./update-genre.component.css']
})
export class UpdateGenreComponent implements OnInit {

  id: number;
  genre: Genre;
  error: HttpErrorResponse;

  constructor(private route: ActivatedRoute, private router: Router,
              private genreService: GenreService) {
  }

  ngOnInit() {
    this.genre = new Genre();
    this.id = this.route.snapshot.params['id'];

    this.genreService.getGenre(this.id)
      .subscribe(data => {
        console.log(data);
        this.genre = data;
      }, error => console.log(error));
  }

  updateGenre() {
    this.genreService.updateGenre(this.id, this.genre)
      .subscribe(data => {
        console.log(data);
        this.genre = new Genre();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit() {
    this.updateGenre();
  }

  gotoList() {
    this.router.navigate(['/genres']);
  }

}
