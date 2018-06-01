
$(document).ready(function() {

    var options = {
        chart: {
            renderTo: 'ItemsSold',
            type: 'column'
        },
        title: {
            text: 'Sales Per Item',
            x: 10 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            title: {
                text: 'SALES'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>:<b>{point.y}</b> of total<br/>'

        },
        plotOptions: {
            series: {
                borderWidth: 0,

                dataLabels: {
                    enabled: true,
                    format: '{point.y}'
                }
            }
        },
        legend: {
            layout: 'center',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        series: []
    };
    $.getJSON("getOrderItems", function(data) {
        console.log(data);
        var series={
            data: []
        }
        for(i in data)
        {
            options.xAxis.categories.push(data[i].name); //xAxis: {categories: []}
            series.data.push(data[i].numbers);
        }
        options.series.push(series);
        chart = new Highcharts.Chart(options);
    });
});



