import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthorDetailsComponent} from './author/author-details/author-details.component';
import {CreateAuthorComponent} from './author/create-author/create-author.component';
import {AuthorListComponent} from './author/author-list/author-list.component';
import {UpdateAuthorComponent} from './author/update-author/update-author.component';
import {LibraryComponent} from "./library/library.component";
import {BookListComponent} from "./book/book-list/book-list.component";
import {BookDetailsComponent} from "./book/book-details/book-details.component";
import {UpdateBookComponent} from "./book/update-book/update-book.component";
import {CreateBookComponent} from "./book/create-book/create-book.component";
import {UpdateGenreComponent} from "./genre/update-genre/update-genre.component";
import {GenreDetailsComponent} from "./genre/genre-details/genre-details.component";
import {CreateGenreComponent} from "./genre/create-genre/create-genre.component";
import {GenreListComponent} from "./genre/genre-list/genre-list.component";
import {UpdateCommentComponent} from "./comment/update-comment/update-comment.component";
import {CommentDetailsComponent} from "./comment/comment-details/comment-details.component";
import {CreateCommentComponent} from "./comment/create-comment/create-comment.component";
import {CommentListComponent} from "./comment/comment-list/comment-list.component";
import {AuthenticateComponent} from "./authenticate/authenticate.component";

const routes: Routes = [
  {path: 'authors', component: AuthorListComponent},
  {path: 'authors/add', component: CreateAuthorComponent},
  {path: 'authors/update/:id', component: UpdateAuthorComponent},
  {path: 'authors/details/:id', component: AuthorDetailsComponent},

  {path: 'genres', component: GenreListComponent},
  {path: 'genres/add', component: CreateGenreComponent},
  {path: 'genres/update/:id', component: UpdateGenreComponent},
  {path: 'genres/details/:id', component: GenreDetailsComponent},

  {path: 'books', component: BookListComponent},
  {path: 'books/add', component: CreateBookComponent},
  {path: 'books/update/:id', component: UpdateBookComponent},
  {path: 'books/details/:id', component: BookDetailsComponent},

  {path: 'comments', component: CommentListComponent},
  {path: 'comments/add', component: CreateCommentComponent},
  {path: 'comments/update/:id', component: UpdateCommentComponent},
  {path: 'comments/details/:id', component: CommentDetailsComponent},

  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: AuthenticateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
