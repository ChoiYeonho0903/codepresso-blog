$(function(){
    $("#more").click(function() {
        var next_page = parseInt($(this).attr("current-page")) + 1; //this란 클린한 html 태그

        $.ajax({
            method: "GET",
            url: "/post",
            data: {"page": next_page}
        })
        .done(function (response){
            for (var post of response) {
                $("#more-posts").append(
                    "<div class=\"post-preview\">" +
                    "<a href=\"/post/" + post.id + "\">" +
                    "<h2 class=\"post-title\">" +
                    post.title +
                    "</h2>\n" +
                    "<h3 class=\"post-subtitle\">" +
                    post.content +
                    "</h3></a><p class=\"post-meta\">Posted by " +
                    post.username +
                    "</p></div><hr class=\"my-4\" />");
            }
        });
        $(this).attr("current-page", next_page);
    });

    $("#create_button").click(function (){
        let title = $("#post-title").val();
        let username = $("#post-username").val();
        let content = $("#post-content").val();

        $.ajax({
            method: "POST",
            url: "/post",
            data: JSON.stringify({
                "title": title,
                "username": username,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            console.log("Post creation success!");
            window.location.href = "/";
        });
    });

    $("#edit_button").click(function () {
        let title = $("#edit-post-title").val();
        let content = $("#edit-post-content").val();
        let id = $("#edit-post-id").val();

        $.ajax({
            method: "PUT",
            url: "/post",
            data: JSON.stringify({
                "title": title,
                "content": content,
                "id": id
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            console.log("Post update success!");
            window.location.href = "/post/"+id;
        })
    })

    $(".comment-edit").hide();

    $(".comment-edit-form-button").click(function(){
        $(this).closest(".comment_text").find(".comment-edit").show();
    });

    $(".comment-edit-cancel-button").click(function(){
        $(this).closest(".comment_text").find(".comment-edit").hide();
    });
});