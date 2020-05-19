import {Genre} from "../genre";
import { Component, OnInit } from '@angular/core';
import {GenreService} from "../genre.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-genre-details',
  templateUrl: './genre-details.component.html',
  styleUrls: ['./genre-details.component.css']
})
export class GenreDetailsComponent implements OnInit {

  id: number;
  genre: Genre;

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

}
