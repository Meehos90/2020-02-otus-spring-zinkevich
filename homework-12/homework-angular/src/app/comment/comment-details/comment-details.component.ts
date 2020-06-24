import {Comment} from '../comment';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CommentService} from "../comment.service";

@Component({
  selector: 'app-comment-details',
  templateUrl: './comment-details.component.html',
  styleUrls: ['./comment-details.component.css']
})
export class CommentDetailsComponent implements OnInit {

  id: number;
  comment: Comment;

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

}
