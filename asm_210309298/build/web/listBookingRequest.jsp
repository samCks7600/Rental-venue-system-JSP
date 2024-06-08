<%-- 
    Document   : listBookingRequest
    Created on : 2023年4月27日, 上午10:03:05
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="header.jsp" />

    <body>
        <div class="d-flex justify-content-center">
            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                <input type="radio" class="btn-check" name="btnradio" id="btnradio1" value="1" autocomplete="off">
                <label class="btn btn-outline-primary" for="btnradio1">Request</label>
                <input type="radio" class="btn-check" name="btnradio" id="btnradio2" value="2" autocomplete="off" >
                <label class="btn btn-outline-primary" for="btnradio2">Waiting Paying</label>
                <input type="radio" class="btn-check" name="btnradio" id="btnradio3" value="3" autocomplete="off" >
                <label class="btn btn-outline-primary" for="btnradio3">Approve</label>
            </div>
        </div>
        <table class="table table-hover mx-auto mt-3" style="width:80%;" id="bookingTable">
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Venue ID</th>
                    <th>Order Processing Staff</th>
                    <th>Booking date</th>
                    <th>Booking Time</th>
                    <th>Status</th>
                    <th>Fee</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td>${booking.bookingid}</td>
                        <td>${booking.venue.venueName}</td>
                        <td>${booking.staffid}</td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.getTime()}</td>
                        <td>${booking.status}</td>
                        <td>${booking.fee}</td>
                        <td>
                            <c:if test="${booking.status == 'waitingPayment'}">
                                <button id="upload" data-bookingid="${booking.bookingid}" type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal">Upload Payment Proof</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--modal start-->
        <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="uploadModalLabel">Upload Payment Proof</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                    </div>
                    <div class="modal-body">
                        <form  enctype="multipart/form-data" method=“get" action="UpdateBookingStatusServlet">
                            <!--guest section-->
                            <div id="guest-list"></div>
                            <div style="height: 200px; overflow-y: auto;">
                                <div class="guest-fields">
                                    <!-- Guest fields go here -->
                                </div>
                            </div>
                            <div class="mb-3">
                                <button type="button" class="btn btn-primary" id="addGuestBtn">Add Guest</button>
                                <button type="button" class="btn btn-danger" id="deleteGuestBtn">Delete Guest</button>
                            </div>

                            <!--invitation type-->
                            <div class="mb-3">
                                <label for="invitation-format" class="form-label">Invitation Format</label>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="formatRadio"  value="1">
                                    <label class="form-check-label">Format One:<span class="badge rounded-pill bg-info">Casual</span></label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="formatRadio"  value="2">
                                    <label class="form-check-label">Format Two:<span class="badge rounded-pill bg-info">Formal</span></label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="paymentProof" class="form-label">Payment Proof</label>
                                <input type="file" class="form-control" id="paymentProof" name="paymentProof" required>
                            </div>
                            <input type="hidden" class="form-control" name="bookingid" value="" required>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">Clear</button>

                                <button type="submit" name="action" value="proof"class="btn btn-primary" id="submitPayment">Submit</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>


        <script>

            // 取得radio按鈕的元素
            var radioButtons = document.getElementsByName("btnradio");

            // 當使用者選擇radio按鈕時觸發
            radioButtons.forEach(function (radio) {
                radio.addEventListener('click', function () {
                    // 取得目前選擇的radio按鈕的值
                    var selectedValue = this.value;

                    // 取得table的tbody
                    var tableBody = document.querySelector('#bookingTable tbody');

                    // 取得table的所有列
                    var rows = tableBody.querySelectorAll('tr');

                    // 遍歷每一列
                    rows.forEach(function (row) {

                        // 取得每一列中的Status值
                        var statusValue = row.querySelector('td:nth-child(6)').textContent;

                        // 如果選擇的值為radio 1，只顯示Status值為'request'的行
                        if (selectedValue === "1") {
                            if (statusValue !== "request") {
                                row.style.display = "none";
                            } else {
                                row.style.display = "";

                            }
                        }

                        // 如果選擇的值為radio 2，只顯示Status值為'waiting payment'的行
                        if (selectedValue === "2") {
                            if (statusValue !== 'waitingPayment') {
                                row.style.display = 'none';
                            } else {
                                row.style.display = '';
                            }
                        }

                        // 如果選擇的值為radio 3，只顯示Status值為'approve'的行
                        if (selectedValue === '3') {
                            if (statusValue !== 'approve') {
                                row.style.display = 'none';
                            } else {
                                row.style.display = '';
                            }
                        }

                    });
                });
            });

//            guest btn
            let guestCount = 0;
            const addGuestBtn = document.getElementById('addGuestBtn');
            const deleteGuestBtn = document.getElementById('deleteGuestBtn');

            const guestFields = document.querySelector('.guest-fields');

            addGuestBtn.addEventListener('click', function () {
                // 增加guestCount
                guestCount++;
                // 新增姓名輸入框
                const nameField = document.createElement('div');
                nameField.classList.add('mb-3');
                nameField.innerHTML = '<label for="guest-name-' + guestCount + '" class="form-label"> Guest Name ' + guestCount + '</label>\n\
                                        <input type="text" class="form-control" id="guest-name-' + guestCount + '" name="guestName[]" required>';
//          
                guestFields.appendChild(nameField);
                // 新增電郵輸入框
                const emailField = document.createElement('div');
                emailField.classList.add('mb-3');
                emailField.innerHTML = '<label for="guest-email-' + guestCount + '" class="form-label"> Guest Email ' + guestCount + '</label>\n\
                                        <input type="email" class="form-control" id="guest-email-' + guestCount + '" name="guestEmail[]" required>';
//               
                guestFields.appendChild(emailField);
            });
            // 刪除guest欄位的功能
            deleteGuestBtn.addEventListener('click', function () {

                // 減少guestCount
                guestCount--;
                // 取得最後一個姓名輸入框和電郵輸入框
                const nameField = guestFields.lastElementChild.previousElementSibling;
                const emailField = guestFields.lastElementChild;
                // 刪除最後一個姓名輸入框和電郵輸入框
                guestFields.removeChild(nameField);
                guestFields.removeChild(emailField);

            });
            $(document).on("click", "#upload", function () {

                var bookingid = $(this).data('bookingid');
                $('input[name="bookingid"]').val(bookingid);
            });
        </script>
    </body>

</html>
