import { mixins } from 'vue-class-component';
import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ITask } from '@/shared/model/task.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import TaskService from './task.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Task extends mixins(JhiDataUtils) {
  @Inject('taskService') private taskService: () => TaskService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public tasks: ITask[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllTasks();
  }

  public clear(): void {
    this.retrieveAllTasks();
  }

  public retrieveAllTasks(): void {
    this.isFetching = true;
    this.taskService()
      .retrieve()
      .then(
        res => {
          this.tasks = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ITask): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeTask(): void {
    this.taskService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.task.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllTasks();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public isSameDay(dl) {
    const current = new Date();
    const [year, month, day] = dl.split('-');
    const currentMonth = this.padTo2Digits(current.getMonth() + 1);
    const currentDay = this.padTo2Digits(current.getDate());
    //const curentDateStr = current.getFullYear() + "-" + currentMonth + " - " + currentDay;
    return year == current.getFullYear() && month == currentMonth && day == currentDay;
  }

  // padTo2Digits
  public padTo2Digits(num: number) {
    return num.toString().padStart(2, '0');
  }

  public currentDateTime() {
    console.log('*******************   currentDateTime ****************');
    const current = new Date();
    const date = current.getFullYear() + '-' + (current.getMonth() + 1) + '-' + current.getDate();
    const time = current.getHours() + ':' + current.getMinutes() + ':' + current.getSeconds();
    const dateTime = date + ' ' + time;

    return dateTime;
  }

  public currentDate() {
    console.log('*******************   currentDate ****************');
    const current = new Date();
    const date = current.getFullYear() + '-' + (current.getMonth() + 1) + '-' + current.getDate();
    return date;
  }
}
