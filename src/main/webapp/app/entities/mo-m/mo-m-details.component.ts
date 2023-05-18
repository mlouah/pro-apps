import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IMoM } from '@/shared/model/mo-m.model';
import MoMService from './mo-m.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class MoMDetails extends mixins(JhiDataUtils) {
  @Inject('moMService') private moMService: () => MoMService;
  @Inject('alertService') private alertService: () => AlertService;

  public moM: IMoM = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.moMId) {
        vm.retrieveMoM(to.params.moMId);
      }
    });
  }

  public retrieveMoM(moMId) {
    this.moMService()
      .find(moMId)
      .then(res => {
        this.moM = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
