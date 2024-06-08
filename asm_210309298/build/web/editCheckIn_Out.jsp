
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
                <input type="radio" class="btn-check" name="btnradio" id="btnradio3" value="3" autocomplete="off"  checked>
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
                    <th>Check In Time</th>
                    <th>Check Out Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>

                        <c:if test="${booking.status == 'approve'}">
                            <td>${booking.bookingid}</td>
                            <td>${booking.venue.venueName}</td>
                            <td>${booking.staffid}</td>
                            <td>${booking.bookingDate}</td>
                            <td>${booking.getTime()}</td>
                            <td>${booking.status}</td>
                            <td>${booking.checkInTime}</td>
                            <td>${booking.checkOutTime}</td>
                            <td>
                                <button id="upload" data-bookingid="${booking.bookingid}" type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal">Edit CheckIn</button> </td>
                            <td>                               
                                <button id="upload" data-bookingid="${booking.bookingid}" type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal2">Edit CheckOut</button>
                            </td>
                        </c:if>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--modal start-->
        <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="uploadModalLabel">Edit CheckIn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                    </div>
                    <div class="modal-body">
                        <form  enctype="multipart/form-data" method=“get" action="UpdateBookingStatusServlet">
                            <input type="hidden" name="bookingid" value="" /> 

                            <label for="checkInTime">Check In Time:</label>
                            <input type="time" name="checkInTime" id="checkInTime" required />

                            <input type="hidden" name="action" value="checkin" /> 
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <div class="modal fade" id="uploadModal2" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="uploadModalLabel">Edit CheckOut</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                    </div>
                    <div class="modal-body">
                        <form  enctype="multipart/form-data" method=“get" action="UpdateBookingStatusServlet">
                            <input type="hidden" name="bookingid" value="" /> 

                            <label for="checkOutTime">Check Out Time:</label>
                            <input type="time" name="checkOutTime" id="checkOutTime" required />

                            <select name="desc"  required>
                                <option value="undamaged">Nothing Damaged</option>
                                <option value="damaged">Damaged</option>
                            </select>

                            <input type="hidden" name="action" value="checkout" /> 
                            <button type="submit" class="btn btn-primary">Submit</button>
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


            $(document).on("click", "#upload", function () {

                var bookingid = $(this).data('bookingid');
                $('input[name="bookingid"]').val(bookingid);

            });
        </script>
    </body>

</html>

