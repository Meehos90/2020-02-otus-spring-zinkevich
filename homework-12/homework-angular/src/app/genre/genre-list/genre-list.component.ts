import {Observable} from "rxjs";
import {Component, OnInit} from "@angular/core";
import {Router} from '@angular/router';
import {GenreService} from "../genre.service";
import {Genre} from "../genre";
import {TokenStorageService} from "../../auth/token-storage.service";

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent implements OnInit {
  genres: Observable<Genre[]>;
  private roles: string[];
  public authority: string;

  constructor(private genreService: GenreService, private router: Router, private tokenStorage: TokenStorageService) {
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
