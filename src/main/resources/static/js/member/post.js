// 카카오 주소찾기 API
function execPostCode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분

      // 각 주소의 노출 규칙에 따라 주소를 조합함
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로 이를 참고하여 분기

      var roadAddr = data.roadAddress; // 도로명 주소 변수
      var extraRoadAddr = ""; // 참고 항목 변수

      // 법정동명이 있을 경우 추가 (법정리는 제외)
      // 법정동의 경우 마지막 문자가 "동/로/가"로 끝남
      if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
        extraRoadAddr += data.bname;
      }

      // 건물명이 있고, 공동주택일 경우 추가
      if (data.buildingName !== "" && data.apartment === "Y") {
        extraRoadAddr += extraRoadAddr !== "" ? ", " + data.buildingName : data.buildingName;
      }

      // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
      if (extraRoadAddr !== "") {
        extraRoadAddr = " (" + extraRoadAddr + ")";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다
      document.getElementById("postCode").value = data.zonecode;
      document.getElementById("roadAddress").value = roadAddr;

      // 참고항목 문자열이 있을 경우 해당 필드에 넣는다
      if (roadAddr !== "") {
        document.getElementById("extraAddress").value = extraRoadAddr;
      } else {
        document.getElementById("extraAddress").value = "";
      }

      var guideTextBox = document.getElementById("guide");
      // 사용자가 "선택 안함"을 클릭한 경우, 예상 주소라는 표시를 해준다
      if (data.autoRoadAddress) {
        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
        guideTextBox.innerHTML = "(예상 도로명 주소 : " + expRoadAddr + ")";
        guideTextBox.style.display = "block";
      } else {
        guideTextBox.innerHTML = "";
        guideTextBox.style.display = "none";
      }
    },
    animation: true,
  }).open();
}
