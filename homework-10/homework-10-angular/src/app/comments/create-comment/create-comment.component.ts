import {Component, OnInit} from '@angular/core';
import {Comment} from "../comment";
import {HttpErrorResponse} from "@angular/common/http";
import {CommentService} from "../comment.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent implements OnInit {

  comment: Comment = new Comment();
  submitted = false;
  error: HttpErrorResponse;

  constructor(private commentService: CommentService, private router: Router) {
  }

  ngOnInit(): void {
  }

  save() {
    this.commentService.createComment(this.comment)
      .subscribe(data => {
        console.log(data);
        this.comment = new Comment();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/comments']);
  }

}
