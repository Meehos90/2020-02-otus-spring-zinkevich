import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { CreateAuthorComponent } from './author/create-author/create-author.component';
import { AuthorDetailsComponent } from './author/author-details/author-details.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { UpdateAuthorComponent } from './author/update-author/update-author.component';

import { CreateBookComponent } from './book/create-book/create-book.component';
import { BookDetailsComponent } from './book/book-details/book-details.component';
import { BookListComponent } from './book/book-list/book-list.component';
import { UpdateBookComponent } from './book/update-book/update-book.component';

import { CreateGenreComponent } from './genre/create-genre/create-genre.component';
import { GenreDetailsComponent } from './genre/genre-details/genre-details.component';
import { GenreListComponent } from './genre/genre-list/genre-list.component';
import { UpdateGenreComponent } from './genre/update-genre/update-genre.component';

import { CreateCommentComponent } from './comment/create-comment/create-comment.component';
import { CommentDetailsComponent } from './comment/comment-details/comment-details.component';
import { CommentListComponent } from './comment/comment-list/comment-list.component';
import { UpdateCommentComponent } from './comment/update-comment/update-comment.component';

import { LibraryComponent } from './library/library.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CabinetComponent } from './cabinet/cabinet.component';

import { httpInterceptorProviders } from './auth/auth-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    CreateAuthorComponent,
    AuthorDetailsComponent,
    AuthorListComponent,
    UpdateAuthorComponent,
    LibraryComponent,
    CreateBookComponent,
    BookDetailsComponent,
    BookListComponent,
    UpdateBookComponent,
    CreateGenreComponent,
    GenreDetailsComponent,
    GenreListComponent,
    UpdateGenreComponent,
    CreateCommentComponent,
    CommentDetailsComponent,
    CommentListComponent,
    UpdateCommentComponent,
    LoginComponent,
    RegisterComponent,
    CabinetComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
