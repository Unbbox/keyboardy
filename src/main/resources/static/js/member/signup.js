// const registerForm = document.querySelector("form");
const userId = document.getElementById("userId");

function idValid() {
  console.log("아이디 중복 확인 >> ", userId);
  console.log("아이디 중복 rkqt 확인 >> ", userId.value);

  if ((userId.value === null) | (userId.value === "")) {
    alert("아이디를 입력해주세요!");
  } else {
    fetch(`/member/checkId/${userId.value}`)
      .then((response) => response.ok)
      .then((data) => {
        console.log("data >> ", data);
        console.log("data type >> ", typeof data);
        if (data == true) {
          alert("사용 가능한 아이디 입니다.");
        } else {
          alert("이미 존재하는 아이디 입니다!");
        }
      });
  }
}
