// 초기 페이지 설정
showPage("page1"); // 초기 페이지를 "page1"로 설정하여 처음에는 "page1"만 보이게 합니다.

// 페이지 표시 함수
function showPage(pageId) {
  // 모든 페이지 숨기기
  var pages = document.querySelectorAll(".boardPage");
  for (var i = 0; i < pages.length; i++) {
    pages[i].style.display = "none";
  }

  // 선택한 페이지 표시
  document.getElementById(pageId).style.display = "block";
}


// 바스켓 숫자 올리기
document.addEventListener("DOMContentLoaded", function () {
  // 초기 카운트를 0으로 설정
  let basketCount = 0;

  // .menu_add 버튼 클릭 시
  document.querySelectorAll(".menu_add").forEach(function (button) {
    button.addEventListener("click", function () {
      // data-price 속성에서 가격을 가져와 정수로 변환
      const price = parseInt(button.getAttribute("data-price"));
      // 현재 장바구니 개수가 99를 넘지 않도록 확인
      if (basketCount < 99) {
        // basketCount 증가
        basketCount++;
        // .basket_count 업데이트
        document.querySelector(".basket_count").textContent = basketCount;
      }
    });
  });

  // .menu_add2 버튼 클릭 시
  document.querySelectorAll(".menu_add2").forEach(function (button) {
    button.addEventListener("click", function () {
      // data-price 속성에서 가격을 가져와 정수로 변환
      const price = parseInt(button.getAttribute("data-price"));
      // 현재 장바구니 개수가 99를 넘지 않도록 확인
      if (basketCount < 99) {
        // basketCount 증가
        basketCount++;
        // .basket_count 업데이트
        document.querySelector(".basket_count").textContent = basketCount;
      }
    });
  });
});

// 랜덤한 색상을 생성하는 함수
function getRandomColor() {
  const letters = '0123456789ABCDEF';
  let color = '#';
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}

// .user_point img 요소들을 선택하고 랜덤한 색상을 적용합니다
const userPointImages = document.querySelectorAll('.user_point img');
userPointImages.forEach((img, index) => {
  // 1번째부터 7번째 상자까지 빨주노초파남보 색상을 적용
  if (index >= 0 && index <= 6) {
    const colors = ['#FF0000', '#FFA500', '#FFFF00', '#008000', '#0000FF', '#4B0082', '#9400D3'];
    img.style.border = `1px solid ${colors[index]}`;
  } else {
    // 나머지 상자에는 랜덤한 색상을 적용
    img.style.border = `1px solid ${getRandomColor()}`;
  }
});

document.addEventListener("DOMContentLoaded", function () {
  // 모든 .review 요소를 가져옵니다.
  var reviewElements = document.querySelectorAll(".review");

  // 각 .review 요소에 대해 작업합니다.
  reviewElements.forEach(function (reviewElement) {
    // 해당 .review 요소 내부의 .review_img 요소를 가져옵니다.
    var reviewImg = reviewElement.querySelector(".review_img");

    // .review_img 요소가 존재하고 그 안에 자식 이미지가 없다면 높이를 350px로 설정합니다.
    if (reviewImg && reviewImg.children.length === 0) {
      reviewElement.style.height = "350px";
    }
  });
});

document.addEventListener("DOMContentLoaded", function () {
  // select 요소를 가져옵니다.
  var select = document.getElementById("list");

  // scroll_content 요소를 가져옵니다.
  var scrollContent = document.querySelector(".scroll_content");

  // select 요소가 변경될 때 실행될 함수를 정의합니다.
  select.addEventListener("change", function () {
    var selectedValue = select.value;

    // 모든 review 요소들을 가져옵니다.
    var reviews = document.querySelectorAll(".review");

    // review 요소들을 배열로 변환하여 정렬합니다.
    var sortedReviews = Array.from(reviews).sort(function (a, b) {
      if (selectedValue === "1") {
        // 최근순으로 정렬 (review_day 기준으로)
        var dateA = parseReviewDate(a.querySelector(".review_day").textContent);
        var dateB = parseReviewDate(b.querySelector(".review_day").textContent);
        return dateA - dateB;
      } else if (selectedValue === "2") {
        // 별점 높은 순으로 정렬 (user_star 기준으로)
        var starA = parseFloat(a.querySelector(".user_star").textContent.replace("⭐", ""));
        var starB = parseFloat(b.querySelector(".user_star").textContent.replace("⭐", ""));
        return starB - starA;
      } else if (selectedValue === "3") {
        // 별점 낮은 순으로 정렬 (user_star 기준으로)
        var starA = parseFloat(a.querySelector(".user_star").textContent.replace("⭐", ""));
        var starB = parseFloat(b.querySelector(".user_star").textContent.replace("⭐", ""));
        return starA - starB;
      }
    });

    // 정렬된 리뷰를 scroll_content에 추가합니다.
    for (var i = 0; i < sortedReviews.length; i++) {
      scrollContent.appendChild(sortedReviews[i]);
    }
  });

  // '일전'의 숫자를 파싱하는 함수
  function parseReviewDate(dateString) {
    var match = dateString.match(/(\d+)일전/);
    if (match) {
      return parseInt(match[1]);
    }
    return 0;
  }
});
