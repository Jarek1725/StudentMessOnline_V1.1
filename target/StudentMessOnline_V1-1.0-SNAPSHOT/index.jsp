<%
    session.invalidate();
%>
<html>
<head>

    <!--    CSS     -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/indexFile.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/footerStyle.css" />


    <!--    JS scripts      -->
    <script src="scripts/header_scroll.js"></script>

    <!--    FONT AWESOME    -->
    <script src="https://kit.fontawesome.com/713b4a8b2f.js" crossorigin="anonymous"></script>

    <!--     SWIPER     -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">

    <script src="https://unpkg.com/swiper/swiper-bundle.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <!--   FONTS -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,400;0,500;0,600;0,800;1,400&display=swap" rel="stylesheet">

    <!--    TITLE   -->
    <title>Welcome</title>

</head>

    <jsp:include page="header.jsp"/>

<!-- Swiper -->

<div id="conteiner">

    <main>
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <img src="img/swiperImg/pic4.jpg">
                </div>
                <div class="swiper-slide">
                    <img src="img/swiperImg/pic1.jpg">
                </div>
                <div class="swiper-slide">
                    <img src="img/swiperImg/pic2.jpg">
                </div>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
            <!-- Add Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>

        <div id="textRightToSwiper">
            <h2>
                Join us.
            </h2>
            <p>
                Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam vitae nisl ut nibh lobortis cursus eu eget tortor. Phasellus maximus elit ac sollicitudin vulputate. Nam eget felis vitae nisi posuere vehicula.
            </p>
        </div>

    </main>

    <!-- UNDER SWIPER BREAKER -->

    <div id="breaker">
        <h2>Chemistry is out domain</h2>
        <p>Fusce eu dui quis ex tincidunt mollis eget ut purus. Nulla porttitor quam sit amet nibh eleifend ornare. Nullam hendrerit luctus erat. Morbi pretium lacus felis, et vestibulum ligula vestibulum at.</p>
    </div>

    <!-- UNDER BREAKER -->

    <div id="underBreaker">
        <div id="firstStat" class="statsUnderBreaker">
            <div class="flex-wrapper">
                <div class="single-chart">
                    <svg viewbox="0 0 36 36" class="circular-chart blue">
                        <path class="circle-bg"
                              d="M18 2.0845
                              a 15.9155 15.9155 0 0 1 0 31.831
                              a 15.9155 15.9155 0 0 1 0 -31.831">
                        </path>
                        <path class="circle"
                              stroke-dasharray="94, 100"
                              d="M18 2.0845
                              a 15.9155 15.9155 0 0 1 0 31.831
                              a 15.9155 15.9155 0 0 1 0 -31.831">
                        </path>
                        <text x="18" y="20.35" class="percentage">94%</text>
                    </svg>
                </div>
            </div>
            <h3>Users</h3>
            <p>Uses Spirit School <br>
                as daily platform
            </p>
        </div>

        <div id="secondStat" class="statsUnderBreaker">
            <div id="schoolStats">
                <h2>1200+</h2>
            </div>
            <h3>Universitets</h3>
            <p>Recommend Spirit School
            </p>
        </div>

        <div id="thirdStat" class="statsUnderBreaker">
            <img src="img/test.png" style="width: 150px">
            <h3>Over  10 000</h3>
            <p>
                People join us <br>
                every day
            </p>
        </div>
    </div>

</div>

    <jsp:include page="footer.jsp"/>

<!-- Swiper JS -->
<script src="../package/swiper-bundle.min.js"></script>

<!-- Initialize Swiper -->
<script>
    var swiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay: {
            delay: 5000,
        },
        pagination: {
            el: '.swiper-pagination',
            type: 'progressbar',
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
</script>

<script>

    sessionStorage.removeItem('user');

    let DBDeleteRequest = window.indexedDB.deleteDatabase("myDatabase");

    DBDeleteRequest.onerror = function(event) {
        console.log("Error deleting database.");
    };

    DBDeleteRequest.onsuccess = function(event) {
        console.log("Database deleted successfully");
        console.log(event.result);
    };

</script>