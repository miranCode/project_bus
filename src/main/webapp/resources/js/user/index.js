setTimeout(function() {
  const element = document.querySelector('.ra2');


}, 3000); 


setTimeout(function() {
  const element = document.querySelector('.ra2');
  element.style.opacity = '1';      /* 보이게 설정 */

}, 3000); /* 3000ms = 3초 후에 실행 */


setTimeout(function() {
  const element = document.querySelector('.doro1');
  element.style.display = 'none'; /* 3초 후에 .ra 요소를 숨김 */
  
  const element2 = document.querySelector('.doro2');
  element2.style.opacity = '1'; /* .ra2 요소를 보이게 설정 */
}, 3000);


