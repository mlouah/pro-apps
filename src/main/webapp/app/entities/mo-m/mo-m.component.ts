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
}
