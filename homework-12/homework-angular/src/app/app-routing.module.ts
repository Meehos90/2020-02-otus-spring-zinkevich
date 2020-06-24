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
import {CabinetComponent} from "./cabinet/cabinet.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuard} from "./auth/auth.guard";

const routes: Routes = [
  {path: 'authors', component: AuthorListComponent, canActivate: [AuthGuard]},
  {path: 'authors/add', component: CreateAuthorComponent, canActivate: [AuthGuard]},
  {path: 'authors/update/:id', component: UpdateAuthorComponent, canActivate: [AuthGuard]},
  {path: 'authors/details/:id', component: AuthorDetailsComponent, canActivate: [AuthGuard]},

  {path: 'genres', component: GenreListComponent, canActivate: [AuthGuard]},
  {path: 'genres/add', component: CreateGenreComponent, canActivate: [AuthGuard]},
  {path: 'genres/update/:id', component: UpdateGenreComponent, canActivate: [AuthGuard]},
  {path: 'genres/details/:id', component: GenreDetailsComponent, canActivate: [AuthGuard]},

  {path: 'books', component: BookListComponent, canActivate: [AuthGuard]},
  {path: 'books/add', component: CreateBookComponent, canActivate: [AuthGuard]},
  {path: 'books/update/:id', component: UpdateBookComponent, canActivate: [AuthGuard]},
  {path: 'books/details/:id', component: BookDetailsComponent, canActivate: [AuthGuard]},

  {path: 'comments', component: CommentListComponent, canActivate: [AuthGuard]},
  {path: 'comments/add', component: CreateCommentComponent, canActivate: [AuthGuard]},
  {path: 'comments/update/:id', component: UpdateCommentComponent, canActivate: [AuthGuard]},
  {path: 'comments/details/:id', component: CommentDetailsComponent, canActivate: [AuthGuard]},

  {path: 'cabinet', component: CabinetComponent, canActivate: [AuthGuard]},

  {path: 'library', component: LibraryComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', redirectTo: 'library', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
