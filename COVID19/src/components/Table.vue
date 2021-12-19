<template>
  <div id = "table-page">
    <InfoTable stripe border :columns = "columns1" :data = "data1" style = "position: relative; left: -10%;"></InfoTable>
    <div id = "information">

    </div>
    <div id = "operation">
      <Form :model="formItem" :label-width="80">
        <!--
        <FormItem label="Input">
            <Input v-model="formItem.input" placeholder="Enter something..."></Input>
        </FormItem>
         -->
        <FormItem label="Date">
            <Row>
                <Col span="20">
                    <DatePicker type="date" placeholder="Select date" v-model="formItem.date" id = "select-date" @on-change = "selectDate"></DatePicker>
                </Col>
                <!--
                <Col span="2" style="text-align: center">-</Col>
                <Col span="11">
                    <TimePicker type="time" placeholder="Select time" v-model="formItem.time"></TimePicker>
                </Col>
                 -->
            </Row>
        </FormItem>
        <FormItem label="Group By">
          <Col span = "20">
            <Select v-model="formItem.select" id = "group by" @on-change = "selectGroup">
                <Option value="countryName">Country Name</Option>
                <Option value="totCases">Total Cases</Option>
                <Option value="totDeaths">Total Deaths</Option>
                <Option value="totVACs">Total VACs</Option>
                <Option value="newCases">Daily New Cases</Option>
                <Option value="newDeaths">Daily New Deaths</Option>
                <Option value="newVACs">Daily New VACs</Option>
            </Select>
          </Col>
        </FormItem>
        <FormItem label="Order By">
            <RadioGroup v-model="order">
                <Radio v-for="item in orders" :label="item.value" :key="item.value">
                  <span>{{item.label}}</span>
                </Radio>
            </RadioGroup>
        </FormItem>
        <!--
        <FormItem label="Checkbox">
            <CheckboxGroup v-model="formItem.checkbox">
                <Checkbox label="Eat"></Checkbox>
                <Checkbox label="Sleep"></Checkbox>
                <Checkbox label="Run"></Checkbox>
                <Checkbox label="Movie"></Checkbox>
            </CheckboxGroup>
        </FormItem>
         -->
         <!--
        <FormItem label="Switch">
            <i-switch v-model="formItem.switch" size="large">
                <span slot="open">On</span>
                <span slot="close">Off</span>
            </i-switch>
        </FormItem>
         -->
        <!--
        <FormItem label="Slider">
            <Slider v-model="formItem.slider" range></Slider>
        </FormItem>
         -->
         <!--
        <FormItem label="Text">
            <Input v-model="formItem.textarea" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..."></Input>
        </FormItem>
         -->
        <FormItem>
          <ButtonGroup>
            <Button type="primary" shape="circle" icon="ios-search" id = "table-query">Query</Button>
            <Button type = "warning" shape="circle" id = "table-download">Download</Button>
            <!--
            <Col span = "10">
              <Button style="margin-left: 8px">Cancel</Button>
            </Col>
             -->
          </ButtonGroup>
        </FormItem>
      </Form>
    </div>
    <router-view/>
  </div>
</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import { Button, Table, Form, FormItem } from 'view-design'
import 'view-design/dist/styles/iview.css'
Vue.component('Button', Button);
Vue.component('InfoTable', Table);
Vue.component('Form', Form)
Vue.component('FormItem', FormItem)
export default {
  name: "Table",
  data() {
    return {
      option: {},
      formItem: {
          input: '',
          select: '',
          radio: 'male',
          checkbox: [],
          switch: true,
          date: '',
          time: '',
          slider: [20, 50],
          textarea: ''
      },
      orders: [
        {
          label: "Ascending",
          value: "1"
        },
        {
          label: "Decreasing",
          value: "-1"
        }
      ],
      columns1: [
        {
          title: "Country Name",
          key: "countryName",
          align: 'center',
          width: 258
        },
        {
          title: "Total Cases",
          key: "totCases",
          align: 'center',
          width: 170
        },
        {
          title: "Total Deaths",
          key: "totDeaths",
          align: 'center',
          width: 170
        },
        {
          title: "Total VACs",
          key: "totVACs",
          align: 'center',
          width: 170
        },
        {
          title: "Daily New Cases",
          key: "newCases",
          align: 'center',
          width: 170
        },
        {
          title: "Daily New Deaths",
          key: "newDeaths",
          align: 'center',
          width: 170
        },
        {
          title: "Daily New VACs",
          key: "newVACs",
          align: 'center',
          width: 170
        }
      ],
      data1: [

      ],
      date: null,
      group: null,
      order: null,
      json: null
    }
  },
  mounted() {
    let d = new Date()
    let date = `${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()}`
    this.changeOption(date, "totCases", 1)
    let table_query = document.getElementById("table-query")
    table_query.onclick = this.onClickQuery
    let table_download = document.getElementById("table-download")
    table_download.onclick = this.onClickDownload
  },
  methods: {
    selectDate(date) {
      this.date = date
    },
    selectGroup(group) {
      this.group = group
    },
    onClickDownload() {
      var FileSaver = require('file-saver');
      let data = this.json
      var content = JSON.stringify(data);
      var blob = new Blob([content ], {type: "text/plain;charset=utf-8"});
      FileSaver.saveAs(blob, "table.json");
    },
    onClickQuery() {
      this.changeOption(this.date, this.group, this.order)
    },
    changeOption(date, group, order) {
      axios.get(
        "http://10.26.144.47:8080/data/table",
        {
          params: {
            date: date,
            group: group,
            order: order
          }
        }
      )
      .then(response => {
        let data = response.data
        this.json = response.data
        this.data1 = []
        for (let i = 0; i < data.length; i++) {
          this.data1.push({
            countryName: data[i].CountryName,
            totCases: data[i].totCases,
            totDeaths: data[i].totDeaths,
            totVACs: data[i].totVACs,
            newCases: data[i].newCases,
            newDeaths: data[i].newDeaths,
            newVACs: data[i].newVACs
          })
        }
      })
      .catch(function(error) {
        console.log(error)
      })
    },
    setYear() {
      let choose_year = document.getElementById("choose-year")
      for (let i = 2019; i <= new Date().getFullYear(); i++) {
        let op = document.createElement("option")
        op.text = i
        op.value = i
        choose_year.add(op, null)
      }
    },
    setMonth() {
      let choose_year = document.getElementById("choose-year")
      let choose_month = document.getElementById("choose-month")
      choose_month.innerHTML = ""
      let start, end
      let index = choose_year.selectedIndex
      if (choose_year.options[index].value == 2019) {
        start = 10
        end = 12
      } else if (choose_year.options[index].value == new Date().getFullYear()) {
        start = 1
        end = new Date().getMonth() + 1
      } else {
        start = 1
        end = 12
      }
      for (let i = start; i <= end; i++) {
        var op = document.createElement("option")
        op.text = i
        op.value = i
        choose_month.add(op, null)
      }
    },
    setDate() {
      let choose_year = document.getElementById("choose-year")
      let choose_month = document.getElementById("choose-month")
      let choose_date = document.getElementById("choose-date")
      choose_date.innerHTML = ""
      let start, end
      let indexYear = choose_year.selectedIndex
      let year = choose_year.options[indexYear].value
      let indexMonth = choose_month.selectedIndex
      let month = choose_month.options[indexMonth].value
      let days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
      if (year % 4 == 0 && year % 400 != 0) {
        days[2] = 29
      }
      if (year == 2019) {
        start = 10
      } else {
        start = 1
      }
      let date = new Date()
      if (month == date.getMonth() + 1) {
        end = date.getDate()
      } else {
        end = days[month]
      }
      for (let i = start; i <= end; i++) {
        var op = document.createElement("option")
        op.text = i
        op.value = i
        choose_date.add(op, null)
      }
    }
  },
  updated() {

  }
}
</script>

<style>
#table-page {
  width: 1280px;
  height: 640px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
}
#information {
  width: 75%;
  height: 640px;
}
#table1 {
  border-collapse: collapse;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
  border: 3px solid #ff0000;
}
#tr1{
  border-spacing: 30px 30px;
}
#operation {
  width: 300px;
  height: 600px;
  float: right;
  position: fixed;
  right: 20px;
  top: 20%;
}
</style>
