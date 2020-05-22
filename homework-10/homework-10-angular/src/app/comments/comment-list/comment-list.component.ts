import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Comment} from "../comment";
import {CommentService} from "../comment.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {
  comments: Observable<Comment[]>;

  constructor(private commentService: CommentService, private router: Router) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.comments = this.commentService.getCommentsList();
  }

  deleteComment(id: number) {
    this.commentService.deleteComment(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  commentDetails(id: number) {
    this.router.navigate(['comments/details', id]);
  }

  createComment() {
    this.router.navigate(['comments/add']);
  }

  updateComment(id: number) {
    this.router.navigate(['comments/update', id])
  }

}
