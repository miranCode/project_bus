
    $(document).ready(function () {
        $('#rteNm').change(function () {
            const selectedRteNm = $(this).val();

            if (selectedRteNm) {
                // Make an AJAX request to fetch the data
                $.ajax({
                    url: '/qna/list1',
                    type: 'GET',
                    data: { rteNm: selectedRteNm },
                    success: function (response) {
                    console.log(response.count)
                    console.log(selectedRteNm)
                        // Assuming the server responds with a JSON object containing `count`
                        if (response.count != null) {
                            $('#resultText').text(`'${selectedRteNm}'번 불편신고 접수량: ${response.count}`);
                        } else {
                            $('#resultText').text('데이터를 가져오는 중 문제가 발생했습니다.');
                        }
                    },
                    error: function () {
                        $('#resultText').text('서버 요청 중 오류가 발생했습니다.');
                    }
                });
            } else {
                $('#resultText').text('버스를 선택해주세요.');
            }
        });
    });
