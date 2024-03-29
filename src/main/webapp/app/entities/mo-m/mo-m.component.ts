import { mixins } from 'vue-class-component';
import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMoM } from '@/shared/model/mo-m.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import MoMService from './mo-m.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class MoM extends mixins(JhiDataUtils) {
  @Inject('moMService') private moMService: () => MoMService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public moMS: IMoM[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllMoMs();
  }

  public clear(): void {
    this.retrieveAllMoMs();
  }

  public retrieveAllMoMs(): void {
    this.isFetching = true;
    this.moMService()
      .retrieve()
      .then(
        res => {
          this.moMS = res.data;
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

  public prepareRemove(instance: IMoM): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeMoM(): void {
    this.moMService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.moM.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllMoMs();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  // todo: factorisation
  public getShortContent(str, num) {
    if (typeof str === 'string') {
      return str.substring(0, num) + ' ...';
    }
    return str;
  }

  public padTo2Digits(num: number) {
    return num.toString().padStart(2, '0');
  }

  public convertMsToTime(milliseconds: number) {
    let seconds = Math.floor(milliseconds / 1000);
    let minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    seconds = seconds % 60;
    minutes = minutes % 60;

    return `${this.padTo2Digits(hours)}h:${this.padTo2Digits(minutes)}m:${this.padTo2Digits(seconds)}`;
  }

  public getDayDiff(meetingDateStr) {
    let meetingDate = new Date(meetingDateStr);
    let currentDate = new Date();

    const msInDay = 24 * 60 * 60 * 1000;
    let diff = currentDate.getTime() - meetingDate.getTime();
    // 👇️ explicitly calling getTime()
    //return Math.round((currentDate.getTime() - meetingDate.getTime()) / msInDay);
    if (diff < 0) {
      return 'Meeting dans ' + Math.abs(Math.round(diff / msInDay)) + ' jour(s)';
    } else {
      return 'Retard traitement de  ' + Math.round(diff / msInDay) + ' jour(s)';
    }
  }

  // actions du mom traitees a temps ou pas
  public isStatusMoMDelayed(meetingDateStr) {
    let res = true;
    let meetingDate = new Date(meetingDateStr);
    let currentDate = new Date();

    const msInDay = 24 * 60 * 60 * 1000;
    let diff = currentDate.getTime() - meetingDate.getTime();

    if (diff < 0) {
      res = false;
    }
    return res;
  }
}
