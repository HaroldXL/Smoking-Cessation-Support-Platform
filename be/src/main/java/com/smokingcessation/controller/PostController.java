package com.smokingcessation.controller;

import com.smokingcessation.dto.res.PostDTO;
import com.smokingcessation.model.CommunityPost;
import com.smokingcessation.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Xem bài viết của chính mình
    @Operation(
            summary = "Xem bài viết của chính mình"
    )
    @GetMapping("/my")
    public ResponseEntity<List<PostDTO>> getMyPosts(Principal principal) {
        String email = principal.getName();
        List<PostDTO> posts = postService.getMyPosts(email);
        return ResponseEntity.ok(posts);
    }

    //Xem bai viet của người khác qua profile name
    @Operation(
            summary = "Xem bài viết của người khác qua profile name"
    )
    @GetMapping("/{profileName}")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable String profileName) {
        List<PostDTO> posts = postService.getPostsByUserProfileName(profileName);
        return ResponseEntity.ok(posts);
    }

    // Thêm bài viết mới
    @Operation(
            summary = "Thêm bài viết mới"
    )
    @PostMapping
    public ResponseEntity<PostDTO> addNewPost(
            Principal principal,
            @RequestBody PostDTO request) {
        String email = principal.getName();
        PostDTO createdPost = postService.addNewPost(email, request);
        return ResponseEntity.ok(createdPost);
    }

    // API admin duyệt bài viết
    @Operation(
            summary = "duyệt bài role admin"
    )
    @PatchMapping("/{postId}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> approvePost(@PathVariable int postId) {
        PostDTO approvedPost = postService.approvePost(postId);
        return ResponseEntity.ok(approvedPost);
    }

    @Operation(
            summary = "xóa post"
    )
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId,Principal principal
                                        ) {
        String userEmail = principal.getName();
        postService.deletePost(postId, userEmail);
        return ResponseEntity.ok().body("Post deleted successfully");
    }

    @Operation(
            summary = "lấy các bài post theo approved "
    )
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts(
            @RequestParam(required = false) Boolean approved) {
        List<PostDTO> posts = postService.getAllPosts(approved);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable int postId,
            @RequestBody PostDTO postDTO,
            Principal principal) {

        String userEmail = principal.getName();

        PostDTO updatedPost = postService.updatePost(postId, userEmail, postDTO);
        return ResponseEntity.ok(updatedPost);
    }



}
