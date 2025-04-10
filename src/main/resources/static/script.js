console.log("Thymeleaf demo running!");

// Dữ liệu phần trăm (ví dụ về số tiền trong các danh mục)
const data = {
    labels: ['TPB', 'VRE', 'OIL', 'POW'], // Các danh mục
    datasets: [{
        label: 'Portfolio Distribution',
        data: [40, 25, 20, 15], // Các giá trị phần trăm
        backgroundColor: ['#FF5733', '#33FF57', '#3357FF', '#F1C40F'], // Màu sắc cho từng phần
        borderColor: ['#FF5733', '#33FF57', '#3357FF', '#F1C40F'],
        borderWidth: 1
    }]
};

// Thiết lập cấu hình cho biểu đồ tròn
const config = {
    type: 'pie', // Kiểu biểu đồ tròn
    data: data,
    options: {
        responsive: true, // Điều chỉnh theo kích thước màn hình
        plugins: {
            legend: {
                position: 'top', // Vị trí legend
            },
            tooltip: {
                callbacks: {
                    // Tùy chỉnh Tooltip
                    label: function(tooltipItem) {
                        return tooltipItem.label + ': ' + tooltipItem.raw + '%';
                    }
                }
            }
        }
    }
};

// Vẽ biểu đồ tròn vào canvas
const ctx = document.getElementById('portfolioChart').getContext('2d');
new Chart(ctx, config);


// fetch('/api/portfolio/distribution')
//     .then(response => response.json())
//     .then(data => {
//         // Dữ liệu nhận được từ server
//         const labels = data.labels;
//         const portfolioData = data.data;
//
//         // Tạo biểu đồ với dữ liệu lấy từ server
//         const config = {
//             type: 'pie', // Kiểu biểu đồ tròn
//             data: {
//                 labels: labels,
//                 datasets: [{
//                     label: 'Portfolio Distribution',
//                     data: portfolioData,
//                     backgroundColor: ['#FF5733', '#33FF57', '#3357FF', '#F1C40F'],
//                     borderColor: ['#FF5733', '#33FF57', '#3357FF', '#F1C40F'],
//                     borderWidth: 1
//                 }]
//             },
//             options: {
//                 responsive: true,
//                 plugins: {
//                     legend: {
//                         position: 'top',
//                     },
//                     tooltip: {
//                         callbacks: {
//                             label: function(tooltipItem) {
//                                 return tooltipItem.label + ': ' + tooltipItem.raw + '%';
//                             }
//                         }
//                     }
//                 }
//             }
//         };
//
//         const ctx = document.getElementById('portfolioChart').getContext('2d');
//         new Chart(ctx, config);
//     })
//     .catch(error => console.error('Error fetching portfolio data:', error));


document.addEventListener("DOMContentLoaded", function () {
    const rowViewSelect = document.getElementById("row-view");

    function loadTransactions(page, size) {
        fetch(`/home/transactions?page=${page}&size=${size}`)
            .then(res => res.text())
            .then(html => {
                document.getElementById("transaction-history-container").innerHTML = html;
            });

        fetch(`/home/pagination?page=${page}&size=${size}`)
            .then(res => res.text())
            .then(html => {
                document.getElementById("pagination-container").innerHTML = html;
                attachPaginationEvents();
            });
    }

    function attachPaginationEvents() {
        document.querySelectorAll(".pagination a[data-page]").forEach(a => {
            a.addEventListener("click", function (event) {
                event.preventDefault();
                const page = this.getAttribute("data-page");
                const size = rowViewSelect.value;
                loadTransactions(page, size);
            });
        });
    }

    rowViewSelect.addEventListener("change", function () {
        loadTransactions(0, this.value);
    });

    attachPaginationEvents();
});
