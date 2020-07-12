import {Observable} from "rxjs";
import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {GenreService} from "../genre.service";
import {Genre} from "../genre";

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent implements OnInit {
  genres: Observable<Genre[]>;

  constructor(private genreService: GenreService, private router: Router) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.genres = this.genreService.getGenresList();
  }

  deleteGenre(id: number) {
    this.genreService.deleteGenre(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  genreDetails(id: number){
    this.router.navigate(['genres/details', id]);
  }

  createGenre() {
    this.router.navigate(['genres/add']);
  }

  updateGenre(id: number) {
    this.router.navigate(['genres/update', id])
  }

}
