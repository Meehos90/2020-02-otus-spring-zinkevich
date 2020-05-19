import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Comment} from '../comment';
import {ActivatedRoute, Router} from "@angular/router";
import {CommentService} from "../comment.service";

@Component({
  selector: 'app-update-comment',
  templateUrl: './update-comment.component.html',
  styleUrls: ['./update-comment.component.css']
})
export class UpdateCommentComponent implements OnInit {

  id: number;
  comment: Comment;
  error: HttpErrorResponse;

  constructor(private route: ActivatedRoute, private router: Router,
              private commentService: CommentService) {
  }

  ngOnInit() {
    this.comment = new Comment();
    this.id = this.route.snapshot.params['id'];

    this.commentService.getComment(this.id)
      .subscribe(data => {
        console.log(data);
        this.comment = data;
      }, error => console.log(error));
  }

  updateComment() {
    this.commentService.updateComment(this.id, this.comment)
      .subscribe(data => {
        console.log(data);
        this.comment = new Comment();
        this.gotoList();
      }, error => this.error = error);
  }

  onSubmit() {
    this.updateComment();
  }

  gotoList() {
    this.router.navigate(['/comments']);
  }

}
