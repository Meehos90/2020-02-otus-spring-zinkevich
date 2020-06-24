import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse} from "@angular/common/http";
import {Genre} from "../genre";
import {GenreService} from "../genre.service";

@Component({
  selector: 'app-create-genre',
  templateUrl: './create-genre.component.html',
  styleUrls: ['./create-genre.component.css']
})
export class CreateGenreComponent implements OnInit {

  genre: Genre = new Genre();
  submitted = false;
  error: HttpErrorResponse;

  constructor(private genreService: GenreService, private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.genreService.createGenre(this.genre)
      .subscribe(data => {
        console.log(data);
        this.genre = new Genre();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/genres']);
  }

}
